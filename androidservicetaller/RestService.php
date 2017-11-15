<?php

require_once('ServiceDB.php');
/**
 * @author EAM
 */
class RestService {
    
    public function API(){
        header('Content-Type: application/JSON');                
        $method = $_SERVER['REQUEST_METHOD'];
        
        switch ($method) {
        case 'GET':
            $this->GetRequest();
            break;     
        case 'POST':
            $this->PostRequest();
            break;
        case 'PUT':
            $this->updateStudent();
            break; 
        case 'DELETE':
            $this->deleteStudent();
            break;
        default:
            $this->response(405);
            break;
        }
    }
    
 function response($code=200, $status="", $message="") {
    http_response_code($code);
    if( !empty($status) && !empty($message) ){
        $response = array("status" => $status ,"message"=>$message);  
        echo json_encode($response,JSON_PRETTY_PRINT);    
    }            
 }
     
  function GetRequest(){
      $db = new ServiceDB();
      if($_GET['action']=='user'){
          $response['data'] = $db->getUsers();
          echo json_encode($response,JSON_PRETTY_PRINT);
      }else if($_GET['action']=='noticias'){
          $response['data'] = $db->getNoticias();
          echo json_encode($response,JSON_PRETTY_PRINT);
      }else if($_GET['action']=='medidas'){
          if(isset($_GET['id'])){
              $response['data'] = $db->getMedidas($_GET['id']);
              echo json_encode($response,JSON_PRETTY_PRINT);
          }else{
              echo json_encode(false,JSON_PRETTY_PRINT);
          }

      } else if($_GET['action']=='retos'){
          $response['data'] = $db->getRetos();
          echo json_encode($response,JSON_PRETTY_PRINT);
      }else if($_GET['action']=='gimnasios'){
          $response['data'] = $db->getGymnasios();
          echo json_encode($response,JSON_PRETTY_PRINT);
      }else if($_GET['action']=='alimentaciones'){
          $response['data'] = $db->getAlimentaciones();
          echo json_encode($response,JSON_PRETTY_PRINT);
      }else if($_GET['action']=='rutinas'){
          if(isset($_GET['id'])){
              $response['data'] = $db->getRutinas($_GET['id']);
              echo json_encode($response,JSON_PRETTY_PRINT);
          }else{
              $response['data'] = $db->getRutinasPredeterminadas();
              echo json_encode($response,JSON_PRETTY_PRINT);
          }
      }else if($_GET['action']=='img'){
          if(isset($_GET['id'])){
              switch ($_GET['id']){
                  case 0:
                      $directory="imagenes/alimentacion";
                      break;
                  case 1:
                      $directory="imagenes/retos";
                      break;
                  case 2:
                      $directory="imagenes/rutinas";
                      break;
              }

              $RutasImagenes = array();
              $ImagenesCodificadas  = array();
              $Respuesta  = array();
              $dirint = dir($directory);
              while (($archivo = $dirint->read()) !== false)
              {
                  if (eregi("gif", $archivo) || eregi("jpg", $archivo) || eregi("png", $archivo)){
                      array_push($RutasImagenes, $directory."/".$archivo);
                      $im = file_get_contents($directory."/".$archivo);
                      $imdata = base64_encode($im);
                      array_push($ImagenesCodificadas, $imdata);
                  }
              }
              $Respuesta["data"] = $ImagenesCodificadas;
              $dirint->close();
              echo json_encode($Respuesta,JSON_PRETTY_PRINT);
          }else{
              echo json_encode(false,JSON_PRETTY_PRINT);
          }


      }

 }
    
 function PostRequest(){
     $dbConexion = new ServiceDB(); 
     if($_GET['action']=='user'){
         //Decodifica un string de JSON
         $obj = json_decode( file_get_contents('php://input') );
         $objArr = (array)$obj;
         if (empty($objArr)){
             $this->response(422,"error","Nothing to add. Check json");
         }else if(isset($obj->cedula) &&
                  isset($obj->nombre) &&
                  isset($obj->apellido) &&
                  isset($obj->email) &&
                  isset($obj->contraseña)){
             
             $response["data"] = $dbConexion->insertUser( $obj->cedula, $obj->nombre, $obj->apellido, $obj->email, $obj->contraseña);
             echo json_encode($response,JSON_PRETTY_PRINT);
             //$this->response(200,"success","new record added");                             
         }else{
             $this->response(422,"error","The property is not defined");
         }
     } else if($_GET['action']=='login'){
         $obj = json_decode( file_get_contents('php://input') );
         $objArr = (array)$obj;
         if (empty($objArr)){
             $this->response(422,"error","Nothing to add. Check json");
         }else if(isset($obj->email) &&
             isset($obj->contraseña)) {

             $response["data"] = $dbConexion->Login($obj->email, $obj->contraseña);
             if(sizeof($response["data"]) > 0){
                 $response["status"] = "TRUE";
             }else{
                 $response["status"] = "FALSE";
             }
             echo json_encode($response, JSON_PRETTY_PRINT);
             //$this->response(200,"success","new record added");
         }
     }
 }

  function deleteStudent(){
      $db = new ServiceDB();
      if($_GET['action']=='estudiante' && isset($_GET['id'])){            
             $response["data"] = $db->deleteStudent($_GET['id']);                
         echo json_encode($response,JSON_PRETTY_PRINT);
         } 
  }
    
  function updateStudent(){
  $db = new ServiceDB();
  if($_GET['action']=='estudiante'){   
      $obj = json_decode( file_get_contents('php://input') );   
      $objArr = (array)$obj;
      if(isset($obj->codigo) && 
              isset($obj->nombre) &&
              isset($obj->apellido) &&
              isset($obj->cedula) &&
              isset($obj->semestre) &&
              isset($obj->edad)){
          $response["data"] = $db->updateStudent($obj->id,
                                         $obj->codigo,
                                         $obj->nombre,
                                         $obj->apellido,
                                         $obj->cedula,
                                         $obj->semestre,
                                         $obj->edad);                
     echo json_encode($response,JSON_PRETTY_PRINT);
      }else{
         $this->response(422,"error","The property is not defined");
     }
             
    }   
  }
    
}

