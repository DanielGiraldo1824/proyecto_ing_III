<?php 
	
	//importing dbDetails archivo que contiene las variables de conexion de la base de datos
	require_once 'dbDetails.php';

	
	//response array 
	$response = array();
		//checking the required parameters from the request
		
			//connecting to the database 
			$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect...');
			//igualsmos los datos a las siguientes variabls

			//trying para almacenar los datos en la base de datos
			try{
				   $sqls="SELECT f.breakfast,f.lunch,f.dinner FROM food f;";
        			$result=mysqli_query($con,$sqls) or die('Could not look up user information; ' . mysqli_error($con));
        			//valido si existe al menos un registro
           			 while ($row=mysqli_fetch_assoc($result))
            		{
                		$desayno = $row['breakfast'];
                		$almerzo = $row['lunch'];
                		$cena = $row['dinner'];
                		$list[] = array('desayno'=>$desayno,'almerzo'=>$almerzo,'cena'=>$cena);
            		}
            		echo json_encode($list);
        		}
        		catch(Exception $e){
				$response['error']=true;
				$response['message']=$e->getMessage();
				echo json_encode($response);
			}		
			//closing the connection

			mysqli_close($con);

		
	
	?>