<?php 
	
	//importing dbDetails archivo que contiene las variables de conexion de la base de datos
	require_once 'dbDetails.php';

	//response array 
	$response = array();
	if($_SERVER['REQUEST_METHOD']=='POST'){
		//checking the required parameters from the request
		if(isset($_POST['rutina'])&
            isset($_POST['alimentacion'])&
            isset($_POST['user'])&
            isset($_POST['grasa'])&
            isset($_POST['ritmo'])&
            isset($_POST['peso'])&
            isset($_POST['altura'])&
            isset($_POST['fecha'])){
			//connecting to the database 
			$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect...');

			//igualsmos los datos a las siguientes variabls
			$rutina = $_POST['rutina'];
			$alimentacion = $_POST['alimentacion'];
            $user = $_POST['user'];
            $grasa = $_POST['grasa'];
            $ritmo = $_POST['ritmo'];
            $peso = $_POST['peso'];
            $altura = $_POST['altura'];
            $fecha = $_POST['fecha'];

			//trying para almacenar los datos en la base de datos
			try{
				$sql = "INSERT INTO registros( `rutina_id`, `alimentacion_id`, `users_id`, `grasaCorporal`, `ritmoCardiaco`, `peso`, `altura`,`created_at`) VALUES (". $rutina .",". $alimentacion .",". $user .",'". $grasa ."','". $ritmo."','".$peso."','". $altura."','".$fecha."')";
				
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