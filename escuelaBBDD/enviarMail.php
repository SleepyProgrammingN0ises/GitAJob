<?php
require_once '../PHPMailer/PHPMailer.php';

function enviarCorreo3($email, $nombre){
    $to=$email;
    $nombre=$nombre;
    $token = hash(PASSWORD_DEFAULT, rand(15, 100));

    $subject="Confirmación de cuenta";
    $message="Hola, has recibido este mensaje porque has iniciado el registro en nuestra web.\r\n
        Pulsa o copia el siguiente enlace en un navegador para confirmar el registro:\r\n\r\n
        http://localhost/ejemplos/MVCPablo/modelo/verificar.php?email=$email&token=$token";
    $message = wordwrap($message, 70);
    $from='From: noreply@gmail.com' . "\r\n";

    $mail = new PHPMailer();

    try {
        //https://github.com/PHPMailer/PHPMailer/wiki/Troubleshooting
        //Server settings
        $mail->isSMTP();            //Send using SMTP
        $mail->CharSet = "UTF-8";   //Esto para los acentos
        $mail->Encoding = "quoted-printable";
        //$mail->SMTPDebug = SMTP::DEBUG_CONNECTION;  //Modo desarrollo //Enable verbose debug output
        $mail->SMTPDebug = SMTP::DEBUG_OFF;  //Modo Producción
        $mail->Host       = 'smtp.gmail.com';           //Set the SMTP server to send through
        $mail->SMTPAuth   = true;                       //Enable SMTP authentication
        $mail->Username   = ''; //SMTP username
        $mail->Password   = '';   //SMTP password
        $mail->SMTPSecure = 'tls'; //Enable implicit TLS encryption
        $mail->Port       = 587;   //TCP port to connect to; use 587 if you have set SMTPSecure = PHPMailer::ENCRYPTION_STARTTLS

        //Recipients
        $mail->From='noreplay@server.edu';
        $mail->addAddress($to);     //Add a recipient
        $mail->addReplyTo('noreplay@server.edu', 'Information');

        //Content
        $mail->Subject = $subject;
        $mail->Body    = $message;
        $envio=$mail->send();
        if ($envio)
            return "Hemos enviado un Email a tu cuenta para activarla, haz clic en el enlace";
        else
            return "Error '$mail->ErrorInfo', no se ha podido enviar el correo";
    } catch (Exception $e) {
        return "Mensaje no ha sido enviado. Mailer Error: {$mail->ErrorInfo}";
    }
}