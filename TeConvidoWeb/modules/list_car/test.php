<!DOCTYPE html>
<html lang="es">
  <head>
    <?php include $_SERVER["DOCUMENT_ROOT"].'/route.php'; ?>
    <?php include $root.View::$Head; ?>
      
    <?php
    include_once $root.Controller::$Client;
    include_once $root.Controller::$Session;

    $client = new Client($host);

    $user = new User();
    $user -> login = "XperiaS";
    $user -> email = "alexsilva792@gmail.com";
    $user -> password = "65111992";
    $user -> name = "Alejandro";
    $user -> subname = "Silva";

    $ticket = $client ->isCorrectLogin($user -> email, $user->password);
    
    createSession($ticket->login,$ticket-> ticket);
    ?>
    
  </head>

  <body>
      <input type="text" id="car" list="cars" />
      <?php include $root.Module::$ListCar ?>
  </body>
</html>
