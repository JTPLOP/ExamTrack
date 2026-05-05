# _EndPoints de la aplicación (Explicación)_

## `/login`
Es la primera vista que se va a ver donde el usuario tendrá que iniciar sesión, como es una aplicación corporativa no habrá una página de registro de usuario.

## `/home`
Es la vista a la que se nos redirige tras haber iniciado sesión, en esta primera pestaña se tratara de dar un pequeño preview sobre las estadísticas del profesor en los diferentes ámbitos: exámenes creados, gráficas de aprobados y suspensos en los últimos exámenes, básicamente estadísticas, tambien sobre próximas convocatorias cercanas. 

## `/alumnos`
Esta vista recopila todos aquellos alumnos que hayan participado en al menos 1 examen del profesor, incluiremos una paginación donde mostraremos los 10 primeros. (Se buscará incluir como una pequeña descripción del alumno junto a una foto del mismo, esta descripción será las asignaturas, se mostraran máximo tres y lo demos en punto suspensivo). Esta ventana tendrá una redirección a otro endpoint que será al clicar en la imagen del usuario.

### `/alumnos/(referencia alumno)`
Esta nos llevará a una vista customizada propia de cada alumno, aquí se le indicarán los datos del usuario y las estadísticas del mismo (notas medias, rendimiento en exámenes, próximas convocatorias…). Tras las estadísticas meteremos un bloque donde aparecerán las convocatorias a las que asistió listandolos por bloques con un pequeño resumen de todo y con dos botones abajo que ocupen todo el ancho uno que sea inspeccionar y otro editar. Ambas abrirán el mismo pop up pero una te permitirá editarla y otro solo inspeccionarla. (Si clicka en inspeccionar meter un botón que le permita modificarla en el acto).

## `/convocatorias`
Esta lista será un listado de todas las convocatorias que tiene el profesor (tanto activas, como pendientes como acabadas). Aquí básicamente incluiremos unos 5 cuadrados con convocatorias y un borde exterior que indique el estado de la misma, una característica es que el primero a la izquierda superior siempre tendrá el mismo estilo gris con un símbolo (+) que nos permitirá agregar una nueva convocatoria, aquí se abrirá un pop up donde nos permitirá  agregar las nuevas convocatorias, en este pop up tendrá una sección donde se abrirá una lista con los exámenes del profesor y este seleccionara el examen del que quiere crear la convocatoria lo que significa que debe tenerla de antemanos creada,además le incluimos un botón abajo en el medio que nos permita ver las estadísticas de la convocatoria (Será una vista próximamente explicada). Arriba tendremos una barra lateral que nos permitirá hacer los filtrados convenientes por el usuario de la aplicación. 

### `/convocatorias/(referencia convocatoria)`
(Por definir)

## `/misExamenes`
Esta es la siguiente vista, básicamente será el lugar de creación de exámenes y donde se almacenarán los mismos exámenes. Aquí básicamente se listaran por filas dando datos de los mismos exámenes con un botón al lateral derecho que permitirá modificar. Está abrirá un pop up que permitirá rellenar los datos, introduciremos el mismo diseño de pop up tanto para crear como para modificar

## `/miPerfil`
Esta es la vista donde el usuario podrá ver sus datos y modificar aquellos datos permisibles, también tendrá un redirect hacia un logout

## `/logout`
Esta es la vista donde se cerrará la sesión del usuario, aquí básicamente le indicará que la cerró fue cerrada correctamente y se le indicará al usuario una descripción de que la sesión fue cerrada. Abajo le meteremos un call to action para que inicie sesión de nuevo. (Imagen de referencia en el móvil)

