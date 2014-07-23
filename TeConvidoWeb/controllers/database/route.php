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

/**
 * Description of route
 *
 * @author Alejandro Silva <alexsilva792@gmail.com>
 */

class Get {
    static $IS_CORRECT_LOGIN = "/controllers/database/get/is_correct_login.php";
    static $GET_TOWNS = "/controllers/database/get/get_towns.php";
    static $GET_TOWNS_AROUND = "/controllers/database/get/get_towns_around.php";
}

class Update {
    static $UPDATE_GCM = "/controllers/database/update/update_gcm.php";
}

class Remove {
    //put your code here
}

class Insert {
    static $ADD_USER = "/controllers/database/insert/add_user.php";
}
