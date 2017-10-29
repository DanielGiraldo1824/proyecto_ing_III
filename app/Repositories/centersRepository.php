<?php

namespace App\Repositories;

use App\Models\centers;
use InfyOm\Generator\Common\BaseRepository;

class centersRepository extends BaseRepository
{
    /**
     * @var array
     */
    protected $fieldSearchable = [
        'name_center'
    ];

    /**
     * Configure the Model
     **/
    public function model()
    {
        return centers::class;
    }
}
