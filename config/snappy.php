<?php

return array(


    'pdf' => array(
        'enabled' => true,
        //'binary'  => '/usr/local/bin/wkhtmltopdf',
        //'binary' => base_path('vendor/h4cc/wkhtmltopdf-amd64/bin/wkhtmltopdf-amd64'),
        //'binary' => base_path('vendor/h4cc/wkhtmltopdf-i386/bin/wkhtmltopdf-i386'),//server debian
        'binary' => '"C:\Program Files\wkhtmltopdf\bin\wkhtmltopdf.exe"',//server debian
        'timeout' => false,
        'options' => array(),
        'env'     => array(),
    ),
    'image' => array(
        'enabled' => true,
        //'binary'  => '/usr/local/bin/wkhtmltoimage',
        //'binary' => base_path('vendor/h4cc/wkhtmltoimage-i386/bin/wkhtmltoimage-i386'),//server debian
        'binary' => '"C:\Program Files\wkhtmltopdf\bin\wkhtmltoimage.exe"',//server debian
        'timeout' => false,
        'options' => array(),
        'env'     => array(),
    ),


);
