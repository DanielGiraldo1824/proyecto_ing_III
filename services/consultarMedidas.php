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
			$cedula = $_POST['cedula'];
			//igualsmos los datos a las siguientes variabls

			//trying para almacenar los datos en la base de datos
			try{
				   $sqls="SELECT m.grasaCorporal,m.id_Instructor,m.fechaRegistro,m.altura FROM medidas m WHERE m.id_Client = $cedula";
        			$result=mysqli_query( $con,$sqls) or die('Could not look up user information; ' . mysqli_error($con));
        			//valido si existe al menos un registro
           			 while ($row=mysqli_fetch_assoc($result))
            		{
                		$id_Instructor = $row['id_Instructor'];
                		$fechaRegistro = $row['fechaRegistro'];
                		$grasaCorporal = $row['grasaCorporal'];
                		$altura = $row['altura'];

                		$listGym[] = array('grasaCorporal'=>$grasaCorporal,'id_Instructor'=>$id_Instructor,'fechaRegistro'=>$fechaRegistro,'altura'=>$altura);
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