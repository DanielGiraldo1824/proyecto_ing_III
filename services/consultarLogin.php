<?php 
	
	//importing dbDetails archivo que contiene las variables de conexion de la base de datos
	require_once 'dbDetails.php';

	
	//response array 
	$response = array();
	if($_SERVER['REQUEST_METHOD']=='POST'){
		//checking the required parameters from the request
		if(isset($_POST['user'])and isset($_POST['pass'])){
			//connecting to the database 
			$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect...');

			//igualsmos los datos a las siguientes variabls
			$user = $_POST['user'];
            $pass = $_POST['pass'];

			//trying para almacenar los datos en la base de datos
			try{
				$sql = "SELECT COUNT(c.name) as total,address,name FROM client c WHERE c.identificationCard=$user and c.password='$pass'";
				
				//adding the path and name to database 
				$result = mysqli_query( $con,$sql) or die('Could not look up user information; ' . mysqli_error($con));
				if($result){
					$extraido= mysqli_fetch_array($result);
					if($extraido['address'] == "0"){
						$response['error']=true;
						$response['total']=$extraido['total'];
						$response['tipo']=$extraido['address'];
						$response['name']=$extraido['name'];
						echo json_encode($response);
					}else{

						$sql = "SELECT COUNT(i.identificationCardIns) as total,i.address_Ins,i.name_Ins,i.id_Gym FROM instructor i where identificationCardIns = $user ";
						//adding the path and name to database 
						$result = mysqli_query( $con,$sql) or die('Could not look up user information; ' . mysqli_error($con));
						$extraido= mysqli_fetch_array($result);
						$response['error']=true;
						$response['total']=$extraido['total'];
						$response['tipo']=$extraido['address_Ins'];
						$response['name']=$extraido['name_Ins'];
						$response['gym']=$extraido['id_Gym'];
						echo json_encode($response);
					}
					
				}else{
					echo json_encode($response);
				}


			//if some error occurred 
			}catch(Exception $e){
				$response['error']=true;
				$response['message']=$e->getMessage();
				echo $response;
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