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
    <?php include $root.IU::$MyProfile ?>
    <?php $antsRoad = $controller -> createAntsRoad(); ?>
  </head>

  <body>
      
    <?php include $root.View::$Navbar; ?>

    <div class="container">
        <div class="container-teconvido">
            
            <?php include $root.Module::$AntsRoad?>

            <?php $controller -> includeBody(); ?>
                
         </div>
    </div>       
      
    <?php include $root.View::$Footer; ?>
            
  </body>
</html>