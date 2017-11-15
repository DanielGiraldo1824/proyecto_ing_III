<?php
/** 
 * @author EAM 
 */
$hacer_algo = true;

class ServiceDB {
    
    public $mysqli;
    const LOCALHOST = 'localhost';
    const USER = 'root';
    const PASSWORD = '';
    const DATABASE = 'proyectofinalandroid';
    
    public function __construct() {           
        try{
            //conexión a base de datos
            $this->mysqli = new mysqli(self::LOCALHOST, self::USER, self::PASSWORD, self::DATABASE);
        }catch (mysqli_sql_exception $e){
            //Si no se puede realizar la conexión
            http_response_code(500);
            exit;
        }     
    }

    public function getAlimentaciones(){
        $result = $this->mysqli->query("SELECT * FROM alimentaciones ");
        $student = $result->fetch_all(MYSQLI_ASSOC);
        $result->close();
        return $student;
    }

    public function getUsers(){
        $result = $this->mysqli->query("SELECT * FROM users WHERE tipoUsuario ='Deportista'");
        $student = $result->fetch_all(MYSQLI_ASSOC);          
        $result->close();
        return $student; 
    }

    public function getNoticias(){
        $result = $this->mysqli->query("SELECT * FROM noticias");
        $student = $result->fetch_all(MYSQLI_ASSOC);
        $result->close();
        return $student;
    }

    public function getMedidas($UserID){
        $result = $this->mysqli->query("SELECT * FROM registros WHERE users_id = ".$UserID);
        $student = $result->fetch_all(MYSQLI_ASSOC);
        $result->close();
        return $student;
    }

    public function getRetos(){
        $result = $this->mysqli->query("SELECT * FROM retos");
        $student = $result->fetch_all(MYSQLI_ASSOC);
        $result->close();
        return $student;
    }

    public function getRutinas($idUser){
        $result = $this->mysqli->query("SELECT rutinas.nombre, rutinas.descripcion, rutinas.tiporutina FROM registros JOIN rutinas ON registros.rutina_id = rutinas.id WHERE registros.users_id =" . $idUser);
        $student = $result->fetch_all(MYSQLI_ASSOC);
        $result->close();
        return $student;
    }

    public function getRutinasPredeterminadas(){
        $result = $this->mysqli->query("SELECT * FROM `rutinas` WHERE `tiporutina` LIKE '1'");
        $student = $result->fetch_all(MYSQLI_ASSOC);
        $result->close();
        return $student;
    }

    public function getGymnasios(){
        $result = $this->mysqli->query("SELECT * FROM gimnasios");
        $student = $result->fetch_all(MYSQLI_ASSOC);
        $result->close();
        return $student;
    }
    
    public function insertUser($cedula, $nombre, $apellido, $email, $contraseña){

        $stmt = $this->mysqli->prepare("INSERT INTO users(`cedula`, `name`, `lastname`, `email`, `password`, `remember_token`,`created_at`) VALUES (". $cedula .",'". $nombre ."','". $apellido ."','". $email ."','". $contraseña."','null','2017-11-08 00:00:00')");
        $r = $stmt->execute(); 
        $stmt->close();
        return $r;
    }//End insertClient

    public function Login($email, $contraseña){
        $result = $this->mysqli->query("SELECT * FROM users WHERE email = '".$email."' AND password = '".$contraseña."'");
        $student = $result->fetch_all(MYSQLI_ASSOC);
        $result->close();
        return $student;
    }
    
    public function deleteStudent($id){
        $stmt = $this->mysqli->prepare("DELETE FROM estudiante WHERE codigo = '".$id."';");
        $r = $stmt->execute(); 
        $stmt->close();
        return "true";
    }
    
    public function updateStudent($id, $codigo, $nombre, $apellido, $cedula, $semestre, $edad){
        $stmt = $this->mysqli->prepare("UPDATE `estudiante` SET `codigo`='".$codigo."',`nombre`='".$nombre."',`apellido`='".$apellido."',`cedula`='".$cedula."',`semestre`=".$semestre.",`edad`=".$edad." WHERE id = ".$id.";");
        $r = $stmt->execute(); 
        $stmt->close();
        return true;
    }
}

