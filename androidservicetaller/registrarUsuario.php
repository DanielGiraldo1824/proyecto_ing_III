<?php 
	
	//importing dbDetails archivo que contiene las variables de conexion de la base de datos
	require_once 'dbDetails.php';

	//response array 
	$response = array();
	if($_SERVER['REQUEST_METHOD']=='POST'){
		//checking the required parameters from the request
		if(isset($_POST['cedula'])& isset($_POST['nombre'])& isset($_POST['tipousuario'])& isset($_POST['apellido'])& isset($_POST['email'])& isset($_POST['contraseña'])){
			//connecting to the database 
			$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect...');

			//igualsmos los datos a las siguientes variabls
			$cedula = $_POST['cedula'];
			$nombre = $_POST['nombre'];
            $apellido = $_POST['apellido'];
            $email = $_POST['email'];
            $tipousuario = $_POST['tipousuario'];
            $contraseña = $_POST['contraseña'];

			//trying para almacenar los datos en la base de datos
			try{
				$sql = "INSERT INTO users(`cedula`, `name`, `lastname`, `email`, `password`,`tipoUsuario`, `remember_token`,`created_at`) VALUES (". $cedula .",'". $nombre ."','". $apellido ."','". $email ."','". $contraseña."','". $tipousuario."','null','2017-11-08 00:00:00')";
				
				//adding the path and name to database 

				if(mysqli_query($con,$sql)){
                    $response['data']=true;
                    echo json_encode($response);
					}else{
                    $response['data']=false;
                    $response['message']="Fallo la Inserción";
                    echo json_encode($response);
				}


			//if some error occurred 
			}catch(Exception $e){
				$response['data']=false;
				$response['message']=$e->getMessage();
			}		
			//closing the connection
			
			mysqli_close($con);

		}else{
			$response['data']=false;
            $response['message']="Faltan campos";
			echo json_encode($response);
		}

	}

	?>