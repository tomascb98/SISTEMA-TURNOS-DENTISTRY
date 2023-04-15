window.addEventListener('load', function () {
    (function(){
      //con fetch invocamos a la API de peliculas con el método GET
      //nos devolverá un JSON con una colección de peliculas
      const urlPacientes = '/pacientes';
      const settingsPacientes = {
        method: 'GET'
      }
        console.log("por aca entramos 1")
      fetch(urlPacientes,settingsPacientes)
      .then(response => response.json())
      .then(data => {
      //recorremos la colección de peliculas del JSON
         console.log(data);
         for(paciente of data){

            //por cada pelicula armaremos una fila de la tabla
            //cada fila tendrá un id que luego nos permitirá borrar la fila si eliminamos la pelicula
            var table = document.getElementById("pacienteTable");
            var pacienteRow =table.insertRow();
            let tr_id = 'tr_' + paciente.id;
            pacienteRow.id = tr_id;

            //por cada pelicula creamos un boton que muestra el id y que al hacerle clic invocará
            //a la función de java script findBy que se encargará de buscar la pelicula que queremos
            //modificar y mostrar los datos de la misma en un formulario.
            let updateButton = '<button' +
                                      ' id=' + '\"' + 'btn_id_' + paciente.id + '\"' +
                                      ' type="button" onclick="findBy('+paciente.id+',\'paciente\')" class="btn btn-info btn_id">' +
                                      paciente.id +
                                      '</button>';

            //armamos cada columna de la fila
            //como primer columna pondremos el boton modificar
            //luego los datos de la pelicula
            //como ultima columna el boton eliminar
            pacienteRow.innerHTML = '<td>' + updateButton + '</td>' +
                    '<td class=\"td_titulo\">' + paciente.nombre.toUpperCase() + '</td>' +
                    '<td class=\"td_categoria\">' + paciente.apellido.toUpperCase() + '</td>' +
                    '<td class=\"td_categoria\">' + paciente.documento.toUpperCase() + '</td>' +
                    '<td class=\"td_categoria\">' + paciente.email.toUpperCase() + '</td>';

        };
        })

        console.log("por aca entramos 2")
          const urlOdontologos = '/odontologos';
          const settingsOdontologos = {
            method: 'GET'
          }

          fetch(urlOdontologos,settingsOdontologos)
          .then(response => response.json())
          .then(data => {
          //recorremos la colección de peliculas del JSON
             console.log(data);
             for(odontologo of data){

                //por cada pelicula armaremos una fila de la tabla
                //cada fila tendrá un id que luego nos permitirá borrar la fila si eliminamos la pelicula
                var table = document.getElementById("odontologoTable");
                var odontologoRow =table.insertRow();
                let tr_id = 'tr_' + odontologo.id;
                odontologoRow.id = tr_id;

                //por cada pelicula creamos un boton que muestra el id y que al hacerle clic invocará
                //a la función de java script findBy que se encargará de buscar la pelicula que queremos
                //modificar y mostrar los datos de la misma en un formulario.
                let updateButton = '<button' +
                                          ' id=' + '\"' + 'btn_id_' + odontologo.id + '\"' +
                                          ' type="button" onclick="findBy('+odontologo.id+',\'odontologo\')" class="btn btn-info btn_id">' +
                                          odontologo.id +
                                          '</button>';

                //armamos cada columna de la fila
                //como primer columna pondremos el boton modificar
                //luego los datos de la pelicula
                //como ultima columna el boton eliminar
                odontologoRow.innerHTML = '<td>' + updateButton + '</td>' +
                        '<td class=\"td_titulo\">' + odontologo.nombre.toUpperCase() + '</td>' +
                        '<td class=\"td_categoria\">' + odontologo.apellido.toUpperCase() + '</td>' +
                        '<td class=\"td_categoria\">' + odontologo.numeroMatricula.toUpperCase() + '</td>';
            };
          })
    })
    (function(){
          let pathname = window.location.pathname;
          if (pathname == "/registroTurno.html") {
              document.querySelector(".nav .nav-item a:last").addClass("active");
          }
        })

    const formulario = document.querySelector('#post_turno_form');

        //Ante un submit del formulario se ejecutará la siguiente funcion
        formulario.addEventListener('submit', function (event) {

           //creamos un JSON que tendrá los datos de la nueva película
            const formData = {
                paciente_id: document.querySelector('#paciente_id').value,
                nombrePaciente: document.querySelector('#nombre_paciente').value,
                odontologo_id: document.querySelector('#odontologo_id').value,
                nombreOdontologo: document.querySelector('#nombre_odontologo').value,
                fecha: document.querySelector('#fecha_turno_escogida').value,
            };
            console.log(JSON.stringify(formData));
            //invocamos utilizando la función fetch la API peliculas con el método POST que guardará
            //la película que enviaremos en formato JSON
            const url = '/turnos';
            const settings = {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData)
            }

            fetch(url, settings)
                .then(response => response.json())
                .then(data => {
                     //Si no hay ningun error se muestra un mensaje diciendo que la pelicula
                     //se agrego bien
                     let successAlert = '<div class="alert alert-success alert-dismissible">' +
                         '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                         '<strong></strong> Odontologo Registrado </div>'

                     document.querySelector('#response').innerHTML = successAlert;
                     document.querySelector('#response').style.display = "block";
                     resetUploadForm();

                })
                .catch(error => {
                        //Si hay algun error se muestra un mensaje diciendo que la pelicula
                        //no se pudo guardar y se intente nuevamente
                        let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                         '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                         '<strong> Error intente nuevamente</strong> </div>'

                          document.querySelector('#response').innerHTML = errorAlert;
                          document.querySelector('#response').style.display = "block";
                         //se dejan todos los campos vacíos por si se quiere ingresar otra pelicula
                         resetUploadForm();})
        });

    })


function findBy(id,tipo) {
console.log("Entramos 3")
         if(tipo=="paciente"){
             document.querySelector('#div_paciente_table').style.display = "none"
             document.querySelector('#div_odontologo_table').style.display = "block"
             const url = '/pacientes'+"/"+id;
                       const settings = {
                           method: 'GET'
                       }
                       fetch(url,settings)
                       .then(response => response.json())
                       .then(data => {
                       console.log(data);
                           let paciente = data;
                           document.querySelector('#paciente_id').value = paciente.id;
                           document.querySelector('#nombre_paciente').value=paciente.nombre+" "+paciente.apellido;
                           //el formulario por default esta oculto y al editar se habilita
                           document.querySelector('#div_turno_post').style.display = "block";
                          }).catch(error => {
                           alert("Error: " + error);
                       })
         }
         if (tipo=="odontologo"){
             document.querySelector('#div_odontologo_table').style.display = "none"
             const url = '/odontologos'+"/"+id;
                       const settings = {
                           method: 'GET'
                       }
                       fetch(url,settings)
                       .then(response => response.json())
                       .then(data => {
                           let odontologo = data;
                           document.querySelector('#odontologo_id').value = odontologo.id;
                           document.querySelector('#nombre_odontologo').value = odontologo.nombre+" "+odontologo.apellido;
                           //el formulario por default esta oculto y al editar se habilita
                           document.querySelector('#div_turno_post').style.display = "block";
                       }).catch(error => {
                           alert("Error: " + error);
                       })
         }
 }