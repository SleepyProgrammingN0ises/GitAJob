<?php
function verExamenesHechos(PDO $cnx, $idus) {
    $sqlAlu = "SELECT nombre, apellidos FROM usuarios WHERE IDU LIKE '{$idus}'";
    $sql = "SELECT * FROM calificacionesexam WHERE IDU LIKE '{$idus}'";
    $result = $cnx->query($sql);
    if ($result->rowCount() > 0) {
        //Ha encontrado los examenes
        echo "<div class='contFlex'>";
        $arr = $result->fetch(PDO::FETCH_ASSOC);
        while ($arr != null) { //cada examen crea una tabla
            echo "<table class='tabla2'>";
            echo "<thead>
                    <th colspan='2'>Examen {$arr['IDExam']}</th>
                    </thead>";
            echo "<tbody>";
                    foreach ($arr as $clave => $v) {
                        if ($clave == 'calificacion' || $clave == 'fecha' || $clave == 'curso') {
                            echo "<tr>
                                <td><label>$clave</label></td>
                                <td>$v</td>
                            </tr>";
                        }
                    }
                    echo "<tr><td colspan='2'><button name='hacerExam'>Hacer exámen</button></td></tr>";
            echo "</tbody>";
            echo "</table>";
            $arr = $result->fetch(PDO::FETCH_ASSOC);
        }
        echo "</div>";

        $mediaSQL = "SELECT AVG(calificacion) FROM calificacionesexam WHERE IDU LIKE '{$idus}'";
        echo "<h3>Nota media</h3>";
        $res = $cnx->query($mediaSQL);
        if ($res->rowCount() > 0) {
            $arr = $res->fetch(PDO::FETCH_ASSOC);
            foreach ($arr as $valor) {
                echo "<p style='font-weight: bold; font-size: 24px;'>{$valor}</p>";
            }
        }
    } else {
        $_SESSION['errors'] = "NO HAY EXAMENES en la BBDD";
    }
}

function elegirExamen(PDO $cnx) {
    $sql = "SELECT titulo FROM examenes ORDER BY 1";
    $result = $cnx->query($sql);
    if ($result->rowCount() > 0) {
        $arr = $result->fetch(PDO::FETCH_ASSOC);
        echo "<option>{$arr['titulo']}</option>";
    }
}

function recogerIDus(PDO $cnx) {
    $email = $_SESSION['email'];
    $sql = "SELECT IDU FROM usuarios WHERE email LIKE '{$email}'";
    $result = $cnx->query($sql);
    if ($result->rowCount() > 0) {
        $arr = $result->fetch(PDO::FETCH_ASSOC);
        return $arr['IDU'];
    }
    return null;
}

function verMedia($cnx, $idus) {

}

