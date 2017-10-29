<?php

/*
|--------------------------------------------------------------------------
| Model Factories
|--------------------------------------------------------------------------
|
| Here you may define all of your model factories. Model factories give
| you a convenient way to create models for testing and seeding your
| database. Just tell the factory how a default model should look.
|
*/

$factory->define(App\User::class, function (Faker\Generator $faker) {
    static $password;

    return [
        'name' => $faker->name,
        'email' => $faker->safeEmail,
        'password' => $password ?: $password = bcrypt('secret'),
        'remember_token' => str_random(10),
    ];
});

$factory->define(App\Models\patient::class, function (Faker\Generator $faker) {
    static $password;

    return [
        'city_id' => function () {
            return factory(App\Models\City::class)->create()->id;
        },
        'rh_id' => '1',
        'eps_id' => function () {
            return factory(App\Models\eps::class)->create()->id;
        },
        'identification' => rand(100000000,999999999),
        'genre' => 'M',
        'first_name' => $faker->first_name,
        'lastname' => $faker->lastname,
        'lastname' => $faker->lastname,        
        'email' => $faker->safeEmail,
        'password' => $password ?: $password = bcrypt('secret'),
        'remember_token' => str_random(10),
    ];
});
