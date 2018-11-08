//variables globales
const ENDPOINT = "http://localhost:8080/PrestamosAPI/";
var ulEditoriales = document.getElementById('ulEditoriales');
var mensaje = document.getElementById('mensaje');
var editoriales = [];
var editorial;

//Document Ready
window.addEventListener("load", function(event) {
    console.log("Todos los recursos terminaron de cargar, comenzamos a jugar!");
    cargarEditoriales();
});

function cargarEditoriales(){
    console.log('cargarEditoriales');
   
    ulEditoriales.innerHTML = "";                   //varciar lista    
    var request = new XMLHttpRequest();             //llamada Ajax

    //asincrono
    request.onreadystatechange = function() {
        if( request.readyState === 4 ){
            if ( request.status === 200 ){
                console.log('response 200 '  + request.responseText);
                editoriales = JSON.parse(request.responseText);
                console.log('editoriales %o', editoriales );

                var lis = "";
                editoriales.forEach( (editorial, index) => {                    
                    lis +=
                     `<li onclick="editorialClick(${index}, event)" class="list-group-item"> 
                            
                            <div class="row justify-content-arround">
                                <div class="col">
                                   <p>${editorial.id} ${editorial.nombre} </p>
                                </div>
                                <div class="col text-right">
                                    <i class="fas fa-trash-alt text-danger" onclick="eliminar(${editorial.id})"></i>
                                </div>
                            <div>
                            </li>`;
                });
                ulEditoriales.innerHTML = lis;
                mensaje.textContent = '';
            }
        }        
    };//onreadystatechange  
    request.open('GET', ENDPOINT + 'editoriales');
    request.send();

}//cargarEditoriales


function editorialClick(posicion, event){
    console.log(`editorialClick ${posicion}`);   
    document.querySelectorAll('#ulEditoriales li').forEach( el => el.classList.remove('active'));      
    event.target.classList.add('active');
}

function crear(){

    var nombre = document.getElementById('iNombre').value;
    console.log(`click crear nombre= ${nombre}`);

    var data = {"nombre": nombre};                  //json a enviar
    var request = new XMLHttpRequest();             //llamada Ajax    
    

    request.onreadystatechange = function() {
        if( request.readyState === 4 ){
            if ( request.status === 201 ){
                console.log('response 201 '  + request.responseText);
                editorial = JSON.parse(request.responseText); 
                cargarEditoriales();               
            }
            if ( request.status === 409 ){
                var responseError = JSON.parse(request.responseText);//recoger mensajes errores
                console.warn('409 Conflicto ' + request.responseText);
                responseError.errores.forEach(el=>{
                    lis+=`<li>${el}<li>`;
                });
                document.getElementById('ulErrores').innerHTML(lis);
            }    
        }        
    };//onreadystatechange  
    request.open('POST', ENDPOINT + 'editoriales'); 
    request.setRequestHeader('content-type','application/json');   
    request.send(JSON.stringify(data));

}

function eliminar(id){

    var request = new XMLHttpRequest();
    //asincrono
    request.onreadystatechange = function() {

        if( request.readyState === 4 ){
            if ( request.status === 200 ){ 
                //OK                
                console.log('elimnado 200 ok ' + request.responseText);
                  cargarEditoriales();  
            }
            if ( request.status === 409 ){
                var responseError = JSON.parse(request.responseText);//recoger mensajes errores
                console.warn('409 Conflicto ' + request.responseText);
                var lis = "";
                responseError.errores.forEach(el=>{
                    lis+=`<li>${el}<li>`;
                });

                document.getElementById('ulErrores').innerHTML(lis);
            }
            if(request.status === 404){
                var responseError = JSON.parse(request.responseText);//recoger mensajes errores
                var lis = "";
                responseError.errores.forEach(el=>{
                    lis+=`<li>${el}<li>`;
                });
                document.getElementById('ulErrores').innerHTML = lis;
            }
        }        
    };
    
    //onreadystatechange  
    request.open('DELETE', ENDPOINT + 'editoriales/'+id);
    request.send();

}