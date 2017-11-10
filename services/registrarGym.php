<?php 
	
	//importing dbDetails archivo que contiene las variables de conexion de la base de datos
	require_once 'dbDetails.php';

	
	//response array 
	$response = array();
	if($_SERVER['REQUEST_METHOD']=='POST'){
		//checking the required parameters from the request
		if(isset($_POST['cedula'])and isset($_POST['gym'])and isset($_POST['fecha'])){
			//connecting to the database 
			$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect...');

			//igualsmos los datos a las siguientes variabls
			$cedula = $_POST['cedula'];
			$gym = $_POST['gym'];
			$fecha = $_POST['fecha'];

			//trying para almacenar los datos en la base de datos
			try{
				$sql = "INSERT INTO `register` (`id_Client`, `id_Gym`, `date_Register`) VALUES ('$cedula', '$gym', '$fecha');";
				
				//adding the path and name to database 
				if(mysqli_query($con,$sql)){
					//filling response array with values
					echo "true";
				}else{
					echo "false";
				}

			//if some error occurred 
			}catch(Exception $e){
				$response['error']=true;
				$response['message']=$e->getMessage();
			}		
			//closing the connection

			mysqli_close($con);

		}else{
			$response['error']=true;
			$response['message']='Please choose a file '.$_POST['nombre'];
			echo json_encode($response);
		}

	}

	?>