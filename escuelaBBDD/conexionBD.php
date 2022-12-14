<?php

class conexionBD {
    //Constantes BBDD; cambiar segun el entorno
    private const USERNAME = 'root';
    private const PASSWD = '4321';
    private const BDNAME = 'escueladb';
    private static $_instancia;

    public function __construct() {
        return new PDO('mysql:host=localhost;dbname=' . conexionBD::BDNAME, conexionBD::USERNAME, conexionBD::PASSWD);
    }

    public static function getConexion() {
        if (self::$_instancia == null) {
            try {
                self::$_instancia = new PDO('mysql:host=localhost;dbname=' . conexionBD::BDNAME, conexionBD::USERNAME, conexionBD::PASSWD);
            } catch (PDOException $ex) {
                $_SESSION['errors'] = $ex->getMessage();
            }
            return self::$_instancia;
        } else {
            return self::$_instancia;
        }
    }
    //Ya a partir de haber creado una instancia con esta clase,
    // podremos usar los métodos de los objetos PDO
}