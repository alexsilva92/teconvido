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

function setValueWithGet($name){
    if(!empty($_GET[$name])){
        ?>
        <script> 
        document.getElementById('<?php echo $name;?>').value = 
                '<?php echo $_GET[$name];?>';
        </script> 
        <?php  
    }
}

function setCheckedWithGet($name,$value){
    if(!empty($_GET[$name]) && strcmp($_GET[$name], $value) == 0){
        ?>
        <script> 
        document.getElementById('<?php echo $value;?>').checked = true;
        </script> 
        <?php  
    }
}