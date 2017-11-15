<?php 
	
	//importing dbDetails archivo que contiene las variables de conexion de la base de datos
	require_once 'dbDetails.php';

	
	//response array 
	$response = array();
	if($_SERVER['REQUEST_METHOD']=='POST'){
		//checking the required parameters from the request
		if(isset($_POST['usuario'])& isset($_POST['contraseña'])){
			//connecting to the database 
			$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect...');

			//igualsmos los datos a las siguientes variabls
			$user = $_POST['usuario'];
            $pass = $_POST['contraseña'];

			//trying para almacenar los datos en la base de datos
			try{
				$sql = "SELECT * FROM users WHERE cedula=$user and password='$pass'";
				
				//adding the path and name to database 
				$result = mysqli_query( $con,$sql) or die('Could not look up user information; ' . mysqli_error($con));
				if($result){
                    $row = $result->fetch_array(MYSQL_ASSOC);
					
					$response['data']=$row;
					echo json_encode($response);
				}else{
					echo json_encode($response);
				}


			//if some error occurred 
			}catch(Exception $e){
				$response['data']=true;
				$response['message']=$e->getMessage();
				echo $response;
			}		
			//closing the connection

			mysqli_close($con);

		}else{
			$response['data']=true;
			$response['message']='Please choose a file '.$_POST['nombre'];
			echo json_encode($response);
		}

	}

	?>