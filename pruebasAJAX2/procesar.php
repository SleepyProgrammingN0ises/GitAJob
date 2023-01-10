<?php
require_once "conexionBD.php";

$todoBien = false;

if (isset($_REQUEST['nombre']) && isset($_REQUEST['password'])) {
    $nombre = $_REQUEST['nombre'];
    $contra = $_REQUEST['password'];

    $cnx = conexionBD::getConexion();
    $sql = "SELECT nombre, password FROM usuarios ORDER BY 1";
    $res = $cnx->query($sql);

    if ($res->rowCount()>0) {
        while ($arr = $res->fetch(PDO::FETCH_ASSOC)) {   //comparar la contraseña con la guardada en BBDD
            if (password_verify($contra, $arr['password']) && $arr['nombre'] == $nombre) {
                $todoBien = true;
            }
        }
    }

    if (!$todoBien) {
        unset($_REQUEST['nombre']);
        unset($_REQUEST['password']);
        echo "null";
    } else {
        $arrayData = ["nombre" => $nombre, "contra" => password_hash($contra, PASSWORD_DEFAULT)];
        echo json_encode($arrayData);
    }
} else {

}