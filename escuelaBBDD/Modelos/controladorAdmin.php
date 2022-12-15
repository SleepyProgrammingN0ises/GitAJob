<?php
function crearUsuario($arrayDatos, $cnx) {
    $nom = $arrayDatos[0];
    $ape = $arrayDatos[1];
    $email = $arrayDatos[2];
    $pass = password_hash($arrayDatos[3], PASSWORD_DEFAULT); //PASSWORD_DEFAULT usa hash y salt
    $dir = $arrayDatos[4];
    $local = $arrayDatos[5];
    $prov = $arrayDatos[6];
    $tlf = $arrayDatos[7];
    $cpost = $arrayDatos[8];
    $NIF = $arrayDatos[9];

    $sql = "INSERT INTO usuarios VALUES(NULL, '{$nom}', '{$ape}', '{$pass}', '{$email}' , '{$tlf}',
                             '{$cpost}', '{$local}', '{$prov}', '{$NIF}', '{$dir}', 1)";
    //por defecto, el usuario será Administrador
    $stmnt = $cnx->prepare($sql);
    return $stmnt->execute();
}

function updateUsuario(PDO $cnx, $idUs) {
        $n = $_REQUEST['nomb'];
        $ape = $_REQUEST['apel'];
        $em = $_REQUEST['email'];
        $pass = $_REQUEST['contra'];
        $dir = $_REQUEST['direc'];
        $local = $_REQUEST['local'];
        $prov = $_REQUEST['prov'];

        $sql = "UPDATE usuarios SET nombre='{$n}', apellidos='{$ape}', email='{$em}', password='{$pass}',
                    direccion='{$dir}', localidad='{$local}', provincia='{$prov}' WHERE IDU=$idUs";
        $res = $cnx->prepare($sql);
        return $res->execute();
}       //retornará True ó False, dependiendo de si ha logrado actualizar la tabla o no


function mostrarCategs(PDO $cnx) {  //Mostrar categorias para los select
    $sql = "SELECT * FROM categoria ORDER BY 1";
    $res = $cnx->query($sql);
    $array = $res->fetch(PDO::FETCH_ASSOC);

    while ($array != null) {
        echo "<option>{$array['categoria']}</option>";
        $array = $res->fetch(PDO::FETCH_ASSOC);
    }
}

function listarUsuariosAdmin(PDO $cnx) {
    $found = false;
    $sql = "SELECT * FROM usuarios ORDER BY 1";
    $res = $cnx->query($sql);
    if ($res->rowCount() > 0) {
        echo "<form action='' method='post'>";
        echo "<select name='users'>";
      $arr = $res->fetch(PDO::FETCH_ASSOC);
      while ($arr !== false) {
         $found = true;
         $string = $arr['apellidos'] . '-- ' .  tiposUser($arr['estado']);
         echo "<option>{$string}</option>";
         $arr = $res->fetch(PDO::FETCH_ASSOC);
        }
    echo "</select>";
      echo "<br>";
      echo "<input id='adm' type='radio' name='tipos' value='1'/> <label for='adm'>Admin</label>";
        echo "<input id='prof' type='radio' name='tipos' value='2'/> <label for='prof'>Profesor</label>";
      echo "<input id='alum' type='radio' name='tipos' value='3'/> <label for='alum'>Alumno</label>";
    echo "<br>";
    echo "<button name='cambiarUsu'>
                SET
            </button>";
    echo "</form>";
    } else {
        $found = false;
        $_SESSION['errors'] = "ERROR; No se encuentran usuarios en la BD!";
    }
    return $found;
}

function tiposUser($numTipo) {
    switch ($numTipo) {
        case 1:
            return "ADMIN";
        case 2:
            return "Profesor";
        case 3:
            return "Alumno";
    }
    return null;
}

function cambiarTipoUsuario(PDO $cnx, $tipo, $apel) {
    $sql = "UPDATE usuarios SET estado='{$tipo}' WHERE apellidos LIKE '{$apel}'";
    $stmnt = $cnx->prepare($sql);
    return $stmnt->execute();
}

function insertarCategoria(PDO $cnx, $nombre) {
    $sql = "INSERT INTO categoria VALUES('{$nombre}')";

    $stmnt = $cnx->prepare($sql);
    return $stmnt->execute();
}

function mostrarTablaCategs(PDO $cnx) {
    $sql = "SELECT * FROM categoria ORDER BY 1";
    $result = $cnx->query($sql);
    $arr = $result->fetch(PDO::FETCH_ASSOC);
    echo "<form action='' method='post'>";
    echo "<table>";
        echo "<thead>
            <th colspan='2'>LISTADO CATEGORÍAS</th>
            <button class='buton1' name='agregarCateg'>+AÑADIR</button>
              </thead>";
        echo "<tbody>";
        while ($arr != null) {
            echo "<tr><td>{$arr['categoria']}</td>";
            echo "<td><button class='buton1' name='borrCateg'>-Borrar-</button></td></tr>";
            $arr = $result->fetch(PDO::FETCH_ASSOC);
        }
        echo "</tbody>";
    echo "</table>";
    echo "</form>";
}

function mostrarTablaCursos(PDO $cnx) {
    $sql = "SELECT * FROM cursos ORDER BY 1";
    $result = $cnx->query($sql);
    $arr = $result->fetch(PDO::FETCH_ASSOC);
    echo "<form action='' method='post'>";
    echo "<table>";
    echo "<thead>
            <th>LISTADO CURSOS</th>
            <button class='buton1' name='agregarCurso'>+</button>
              </thead>";
    echo "<tbody>";
    while ($arr != null) {
        echo "<tr><td>{$arr['curso']}</td>";
        $arr = $result->fetch(PDO::FETCH_ASSOC);
    }
    echo "</tbody>";
    echo "</table>";
    echo "</form>";
}

function insertarCurso() {
    echo "<input type='text' name='newCurso' placeholder='Nombre del Curso' required aria-required='true'/>";
    echo "<button name='insCurso'>Crear curso</button>";
}
function insertarCursoBD(PDO $cnx, $nombre) {
    $sql = "INSERT INTO cursos VALUES('{$nombre}')";
    $stmnt = $cnx->prepare($sql);
    return $stmnt->execute();
}

/**
 * INSERTAR PREGUNTA
 * @param $arrayPregunta
 * @return bool|null
 *
 *  Se encarga de recoger el tipo de pregunta que intentamos crear,
 *  así como el enunciado, las respuestas posibles y respuestas correctas.
 */
function insertarPregunta($arrayPregunta) {
    $categoria = $arrayPregunta['categ'];
    $enunciado = $arrayPregunta['nomP'];
    $tipo = $arrayPregunta['pregT'];
    switch ($tipo) {
        case 'S':
            $respCorrecta = $arrayPregunta['sino'];
            $respPosibles = 'v/f';
            break;
        case 'RC':
            $respCorrecta = $arrayPregunta['respCorta'];
            $respPosibles = "";   //string vacío, ya que permitiremos cualquier string como respuesta
        //luego comprobaríamos [al recoger las respuestas en el examen]
            break;
        case 'RM':
            //Procesado de respuestas múltiples;
            // primero, se guardan en $stringArray las CORRECTAS, y se meten en respCorrecta
            // luego, en respPosibles el resto de respuestas
            $respCorrecta = "";
            $stringArray = $arrayPregunta['respuestas[]'];
            for ($i=0;$i<sizeof($stringArray);$i++) {
                if ($i == sizeof($stringArray) - 1) {
                    $respCorrecta .= $stringArray[$i];  //Cogemos todos los valores, en el penúltimo evitamos ponerle la coma
                } else {
                    $respCorrecta .= $stringArray[$i] . ', ';
                }
            }
            $i=0;
            while ($i<$arrayPregunta['numResp']) {
                $str = 'resp' . $i;
                $i++;
            }
            $respPosibles = $str;
            break;
        default:
            return null;
    }
    $cnx = conexionBD::getConexion();
    $sql = "INSERT INTO bancopreguntas VALUES (null, '{$categoria}', '{$tipo}', '{$enunciado}', '{$respCorrecta}', '{$respPosibles}')";
    $stmnt = $cnx->prepare($sql);
    return $stmnt->execute();
}