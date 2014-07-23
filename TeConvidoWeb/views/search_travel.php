<?php

/* 
 * Copyright 2014 Alejandro Silva <alexsilva792@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

?>

<!DOCTYPE html>
<html lang="es">
  <head>
    <?php include $_SERVER["DOCUMENT_ROOT"].'/route.php'; ?>
    <?php include $root.View::$Head; ?>
    <?php include $root.Module::$InputTown ?>
    <link rel="stylesheet" href="/public/jquery/jquery-ui.css">
  </head>

  <body>
    <?php include $root.View::$Navbar; ?>

    <div class="container">
          <div class="panel-center">
              <a href="">Buscar Viaje</a> > ...
              
              <div class="search-travel text-center">
                <form class="form-inline" role="form">
                  <div class="form-group ui-widget">
                    <label for="origin"></label>
                    <input id="origin" type="text" placeholder="Origen" class="form-control" required>
                  </div>
                  <div class="form-group ui-widget">
                    <label for="tags"></label>
                    <input id="destination" type="text" placeholder="Destino" class="form-control" required>
                  </div>
                  <div class="form-group">
                    <input type="date" placeholder="Fecha" class="form-control" required>
                  </div>
                  <button type="text" class="btn btn-primary">Buscar</button>
                </form>
              </div>
              
              <div class="text-center">
                    <img class="img-search-travel " src="/public/images/map_teruel_800x500.jpg" alt="Mapa Teruel">
              </div>
          </div>
      </div>    
      
    <?php include $root.View::$Footer; ?>
  </body>
</html>