<?php 
	
	//importing dbDetails archivo que contiene las variables de conexion de la base de datos
	require_once 'dbDetails.php';

	
	//response array 
	$response = array();
	if($_SERVER['REQUEST_METHOD']=='POST'){
		//checking the required parameters from the request
		if(isset($_POST['grasa'])and isset($_POST['altura'])and isset($_POST['ritmo'])and isset($_POST['fecha'])){
			//connecting to the database 
			$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect...');

			//igualsmos los datos a las siguientes variabls
			$grasa = $_POST['grasa'];
			$altura = $_POST['altura'];
			$ritmo = $_POST['ritmo'];
            $fecha = $_POST['fecha'];
            $gym = $_POST['gym'];
            $instructor = $_POST['instructor'];
            $cliente = $_POST['cliente'];

			//trying para almacenar los datos en la base de datos
			try{
				$sql = "INSERT INTO `medidas` (`id_Client`, `id_Instructor`, `grasaCorporal`, `altura`, `ritmoCardiaco`, `fechaRegistro`) VALUES ($cliente, $instructor, '$grasa', '$altura', '$ritmo', '$fecha');";
				
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
			$response['message']='Please choose a file '.$_POST['grasa'];
			echo json_encode($response);
		}

	}

	?>