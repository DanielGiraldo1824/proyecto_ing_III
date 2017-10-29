<?php

namespace App\Repositories;

use App\Models\users;
use InfyOm\Generator\Common\BaseRepository;

class usersRepository extends BaseRepository
{
    /**
     * @var array
     */
    protected $fieldSearchable = [
        'name',
        'email',
        'password',
        'remember_token'
    ];

    /**
     * Configure the Model
     **/
    public function model()
    {
        return users::class;
    }
}
