<?php

namespace App\Http\Controllers;
use App\Http\Controllers\Controller;

class UtilsController extends Controller
{
   /**
     *  Obtener fecha = $splitTimeStamp[0];
     *  Obtener hora = $splitTimeStamp[1];
     **/
    public static function separateTimestamp($date){
        $timestamp = date('d-m-Y|h:i:s A', strtotime($date));
        $splitTimeStamp = explode("|",$timestamp);
        return $splitTimeStamp[0];
    }
    
     public static function getAge($fecha){
        $fecha = str_replace("/","-",$fecha);
        $fecha = date('Y/m/d',strtotime($fecha));
        $hoy = date('Y/m/d');
        $edad = $hoy - $fecha;
        return $edad;
    }
}
