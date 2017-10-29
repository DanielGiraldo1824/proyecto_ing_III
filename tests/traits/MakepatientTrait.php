<?php

use Faker\Factory as Faker;
use App\Models\patient;
use App\Repositories\patientRepository;

trait MakepatientTrait
{
    /**
     * Create fake instance of patient and save it in database
     *
     * @param array $patientFields
     * @return patient
     */
    public function makepatient($patientFields = [])
    {
        /** @var patientRepository $patientRepo */
        $patientRepo = App::make(patientRepository::class);
        $theme = $this->fakepatientData($patientFields);
        return $patientRepo->create($theme);
    }

    /**
     * Get fake instance of patient
     *
     * @param array $patientFields
     * @return patient
     */
    public function fakepatient($patientFields = [])
    {
        return new patient($this->fakepatientData($patientFields));
    }

    /**
     * Get fake data of patient
     *
     * @param array $postFields
     * @return array
     */
    public function fakepatientData($patientFields = [])
    {
        $fake = Faker::create();

        return array_merge([
            'city_id' => $fake->randomDigitNotNull,
            'rh_id' => $fake->randomDigitNotNull,
            'eps_id' => $fake->randomDigitNotNull,
            'identification' => $fake->word,
            'genre' => $fake->word,
            'name' => $fake->word,
            'lastname' => $fake->word,
            'birth_day' => $fake->word,
            'address' => $fake->word,
            'email' => $fake->word,
            'cellphone_number' => $fake->word,
            'phone_number' => $fake->word,
            'contactNameEmergency' => $fake->word,
            'contactPhoneEmergency' => $fake->word,
            'created_at' => $fake->word,
            'updated_at' => $fake->word
        ], $patientFields);
    }
}
