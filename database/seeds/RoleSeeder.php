<?php

use Illuminate\Database\Seeder;
use HttpOz\Roles\Models\Role;


class RoleSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
       $adminRole = Role::create([
            'name' => 'Admin',
            'slug' => 'admin',
            'description' => '', // optional
            'group' => 'default', // optional, set as 'default' by default
        ]);

        $userRole = Role::create([
            'name' => 'Usuario',
            'slug' => 'user',
        ]);
    }
}
