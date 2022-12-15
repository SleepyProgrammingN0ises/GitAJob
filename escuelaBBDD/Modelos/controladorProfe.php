<?php
function listarPreguntas(PDO $cnx) {
    $sql = "SELECT * FROM bancopreguntas ORDER BY 1";
    $result = $cnx->query($sql);
    echo "<select name='enunPregunta'>";
    if ($result->rowCount() > 0) {
        $arr = $result->fetch(PDO::FETCH_ASSOC);
        while ($arr != null) {
            echo "<option>{$arr['enunciado']}</option>";
            $arr = $result->fetch(PDO::FETCH_ASSOC);
        }
    } else {
        $_SESSION['errors'] = "No se han encontrado preguntas en la BBDD! :[";
        echo "<option>--NO HAY PREGUNTAS EN BD--</option>";
    }
    echo "</select>";
}

function mostrarTablaCursos(PDO $cnx) {
    $sql = "SELECT * FROM cursos ORDER BY 1";
    $result = $cnx->query($sql);
    $arr = $result->fetch(PDO::FETCH_ASSOC);
    echo "<form action='' method='post'>";
    echo "<table>";
    echo "<thead>
            <th>LISTADO CURSOS</th>
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
function mostrarCursosSelect(PDO $cnx) {
    $sql = "SELECT * FROM cursos ORDER BY 1";
    $result = $cnx->query($sql);
    $arr = $result->fetch(PDO::FETCH_ASSOC);
    echo "<select name='curso1'>";
    while ($arr != null) {
        echo "<option>{$arr['curso']}</option>";
        $arr = $result->fetch(PDO::FETCH_ASSOC);
    }
    echo "</select>";
}

function crearExamen($datosExam) {

}