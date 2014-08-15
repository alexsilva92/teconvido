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

$travel = json_decode($_POST['travel']);

?>
<div class="panel panel-primary">
    <div class="panel-heading">
        <h2 class="panel-title">Publicar Viaje</h2>
    </div>

    <div class="panel-body">
        
        <div class="row">
           <div class="col-lg-4">
               <div class="panel panel-info">
                   <div class="panel-heading">
                        <h3 class="panel-title">Información Viaje</h3>
                    </div>
                   <div class="panel-body">
                       <div class="well well-sm">
                           <b>Origen: </b> <?=$travel -> origin?><br><br> 
                           <b>Destino: </b> <?=$travel -> destination?><br><br> 
                           <b>Fecha de salida: </b> <?=$travel -> date?><br><br> 
                           <b>Duración aprox: </b> <?=$travel -> duration?><br><br> 
                           <b>Distancia: </b> <?=$travel -> distance?> <br><br> 
                           <b>Coche: </b> <?=$travel -> car?><br><br> 
                       </div>

                   </div>
               </div>
           </div> 
           
           <div class="col-lg-5">   
                <div class="panel panel-info">
                    <div class="panel-heading">
                             <h3 class="panel-title">Ruta</h3>
                     </div>
                     <div class="panel-body">
                         <iframe
                            width="410"
                            height="255"
                            frameborder="0" style="border:0"
                            src="https://www.google.com/maps/embed/v1/directions?key=AIzaSyAGrDQ_kuUIaAti5dXsqPtDJ8QsgFvieW4&origin=<?=$travel -> origin?>+Spain&destination=<?=$travel -> destination?>+Spain&avoid=tolls|highways">
                        </iframe>
                     </div>
               </div>
            </div>
            
           <div class="col-lg-3">
            <form action="<?=Action::$AddTravelWithDriver?>" method="post">
                <div class="panel panel-success">
                     <div class="panel-heading">
                              <h3 class="panel-title">Importe</h3>
                      </div>
                      <div class="panel-body">
                          <div class="input-group">
                                  <input class="form-control" name="travel-price" type="number" value="1" min="0" max="3" required>
                                  <span class="input-group-addon">&euro;</span>
                          </div>
                      </div>
                     <div class="panel-footer">
                        Esta plataforma tiene la finalidad de que sus usuarios compartan coche y gastos,
                        no se tolerará que un conductor intente sacar beneficio compartiendo coche.<br><br>
                     </div>
                </div>
                <input name="travel" type="hidden" value='<?=json_encode($travel)?>'>
               <button type="submit" class="btn btn-primary btn-lg col-lg-12">Publicar</button>
            </form>
           </div>
       </div>
    </div>
</div>
