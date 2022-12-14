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
    $stmnt = $cnx->prepare($sql);
    return $stmnt->execute();
}

function updateUsuario(PDO $cnx, Usuario $obj, $idUs) {
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

function mostrarCategs(PDO $cnx) {
    $sql = "SELECT * FROM categoria ORDER BY 1";
    $res = $cnx->query($sql);
    $array = $res->fetch(PDO::FETCH_ASSOC);

    while ($array != null) {
        echo "<option>{$array['categoria']}</option>";
        $array = $res->fetch(PDO::FETCH_ASSOC);
    }
}

function mostrarTablaCategs(PDO $cnx) {
    $sql = "SELECT * FROM categoria ORDER BY 1";
    $result = $cnx->query($sql);
    $arr = $result->fetch(PDO::FETCH_ASSOC);
    echo "<table>";
        echo "<thead>
            <th>LISTADO CATEGORÍAS</th>
            <button class='buton1'></button>
              </thead>";
        echo "<tbody>";
        while ($arr != null) {
            echo "<tr><td>{$arr['categoria']}</td></tr>";
            $arr = $result->fetch(PDO::FETCH_ASSOC);
        }
        echo "</tbody>";
    echo "</table>";
}

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