<?php 
	
	//importing dbDetails archivo que contiene las variables de conexion de la base de datos
	require_once 'dbDetails.php';

	
	//response array 
	$response = array();
	if(true){
		//checking the required parameters from the request
		if(true){
			//connecting to the database 
			$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect...');
			$gym = $_POST['gym'];
			//igualsmos los datos a las siguientes variabls

			//trying para almacenar los datos en la base de datos
			try{
				   $sqls="SELECT r.id_Client,r.date_Register FROM register r WHERE r.id_Gym = $gym;";
        			$result=mysqli_query( $con,$sqls) or die('Could not look up user information; ' . mysqli_error($con));
        			//valido si existe al menos un registro
           			 while ($row=mysqli_fetch_assoc($result))
            		{
                		$id_Client = $row['id_Client'];
                		$date_Register = $row['date_Register'];

                		$listGym[] = array('id_Client'=>$id_Client,'date_Register'=>$date_Register);
            		}
            		
            		echo json_encode($listGym);
        		}
        		catch(Exception $e){
				$response['error']=true;
				$response['message']=$e->getMessage();
				echo json_encode($response);
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