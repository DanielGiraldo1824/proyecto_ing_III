<?php

namespace App\Http\Controllers\API;

use App\Http\Requests\API\CreatepatientAPIRequest;
use App\Http\Requests\API\UpdatepatientAPIRequest;
use App\Models\patient;
use App\Models\eps;
use App\Repositories\patientRepository;
use Illuminate\Http\Request;
use App\Http\Controllers\AppBaseController;
use InfyOm\Generator\Criteria\LimitOffsetCriteria;
use Prettus\Repository\Criteria\RequestCriteria;
use Response;

/**
 * Class patientController
 * @package App\Http\Controllers\API
 */

class patientAPIController extends AppBaseController
{
    /** @var  patientRepository */
    private $patientRepository;

    public function __construct(patientRepository $patientRepo)
    {
        $this->patientRepository = $patientRepo;
    }

    /**
     * @param Request $request
     * @return Response
     *
     * @SWG\Get(
     *      path="/patients",
     *      summary="Get a listing of the patients.",
     *      tags={"patient"},
     *      description="Get all patients",
     *      produces={"application/json"},
     *      @SWG\Response(
     *          response=200,
     *          description="successful operation",
     *          @SWG\Schema(
     *              type="object",
     *              @SWG\Property(
     *                  property="success",
     *                  type="boolean"
     *              ),
     *              @SWG\Property(
     *                  property="data",
     *                  type="array",
     *                  @SWG\Items(ref="#/definitions/patient")
     *              ),
     *              @SWG\Property(
     *                  property="message",
     *                  type="string"
     *              )
     *          )
     *      )
     * )
     */
    public function index(Request $request)
    {
        $this->patientRepository->pushCriteria(new RequestCriteria($request));
        $this->patientRepository->pushCriteria(new LimitOffsetCriteria($request));
        $patients = $this->patientRepository->all();

        return $this->sendResponse($patients->toArray(), 'Patients retrieved successfully');
    }

    /**
     * @param CreatepatientAPIRequest $request
     * @return Response
     *
     * @SWG\Post(
     *      path="/patients",
     *      summary="Store a newly created patient in storage",
     *      tags={"patient"},
     *      description="Store patient",
     *      produces={"application/json"},
     *      @SWG\Parameter(
     *          name="body",
     *          in="body",
     *          description="patient that should be stored",
     *          required=false,
     *          @SWG\Schema(ref="#/definitions/patient")
     *      ),
     *      @SWG\Response(
     *          response=200,
     *          description="successful operation",
     *          @SWG\Schema(
     *              type="object",
     *              @SWG\Property(
     *                  property="success",
     *                  type="boolean"
     *              ),
     *              @SWG\Property(
     *                  property="data",
     *                  ref="#/definitions/patient"
     *              ),
     *              @SWG\Property(
     *                  property="message",
     *                  type="string"
     *              )
     *          )
     *      )
     * )
     */
    public function store(CreatepatientAPIRequest $request)
    {
        $input = $request->all();

        $patients = $this->patientRepository->create($input);

        return $this->sendResponse($patients->toArray(), 'Patient saved successfully');
    }

    /**
     * @param int $id
     * @return Response
     *
     * @SWG\Get(
     *      path="/patients/{id}",
     *      summary="Display the specified patient",
     *      tags={"patient"},
     *      description="Get patient",
     *      produces={"application/json"},
     *      @SWG\Parameter(
     *          name="id",
     *          description="id of patient",
     *          type="integer",
     *          required=true,
     *          in="path"
     *      ),
     *      @SWG\Response(
     *          response=200,
     *          description="successful operation",
     *          @SWG\Schema(
     *              type="object",
     *              @SWG\Property(
     *                  property="success",
     *                  type="boolean"
     *              ),
     *              @SWG\Property(
     *                  property="data",
     *                  ref="#/definitions/patient"
     *              ),
     *              @SWG\Property(
     *                  property="message",
     *                  type="string"
     *              )
     *          )
     *      )
     * )
     */
    public function show($id)
    {
        /** @var patient $patient */
        //$patient = $this->patientRepository->findWithoutFail($id);
        $patient = $this->patientRepository->findByField('tag_id', $id);//Find by tag_id
        //$patient['age'] = \App\Http\Controllers\UtilsController::getAge($patient->birth_day);//Find by tag_id
        //$patient->eps_id = eps::find($patient->eps_id)->name ;
        //return $patient;
        if (empty($patient)) {
            return $this->sendError('Patient not found');
        }

        return $this->sendResponse($patient, 'Patient retrieved successfully');
    }

    /**
     * @param int $id
     * @param UpdatepatientAPIRequest $request
     * @return Response
     *
     * @SWG\Put(
     *      path="/patients/{id}",
     *      summary="Update the specified patient in storage",
     *      tags={"patient"},
     *      description="Update patient",
     *      produces={"application/json"},
     *      @SWG\Parameter(
     *          name="id",
     *          description="id of patient",
     *          type="integer",
     *          required=true,
     *          in="path"
     *      ),
     *      @SWG\Parameter(
     *          name="body",
     *          in="body",
     *          description="patient that should be updated",
     *          required=false,
     *          @SWG\Schema(ref="#/definitions/patient")
     *      ),
     *      @SWG\Response(
     *          response=200,
     *          description="successful operation",
     *          @SWG\Schema(
     *              type="object",
     *              @SWG\Property(
     *                  property="success",
     *                  type="boolean"
     *              ),
     *              @SWG\Property(
     *                  property="data",
     *                  ref="#/definitions/patient"
     *              ),
     *              @SWG\Property(
     *                  property="message",
     *                  type="string"
     *              )
     *          )
     *      )
     * )
     */
    public function update($id, UpdatepatientAPIRequest $request)
    {
        $input = $request->all();

        /** @var patient $patient */
        $patient = $this->patientRepository->findWithoutFail($id);

        if (empty($patient)) {
            return $this->sendError('Patient not found');
        }

        $patient = $this->patientRepository->update($input, $id);

        return $this->sendResponse($patient->toArray(), 'patient updated successfully');
    }

    /**
     * @param int $id
     * @return Response
     *
     * @SWG\Delete(
     *      path="/patients/{id}",
     *      summary="Remove the specified patient from storage",
     *      tags={"patient"},
     *      description="Delete patient",
     *      produces={"application/json"},
     *      @SWG\Parameter(
     *          name="id",
     *          description="id of patient",
     *          type="integer",
     *          required=true,
     *          in="path"
     *      ),
     *      @SWG\Response(
     *          response=200,
     *          description="successful operation",
     *          @SWG\Schema(
     *              type="object",
     *              @SWG\Property(
     *                  property="success",
     *                  type="boolean"
     *              ),
     *              @SWG\Property(
     *                  property="data",
     *                  type="string"
     *              ),
     *              @SWG\Property(
     *                  property="message",
     *                  type="string"
     *              )
     *          )
     *      )
     * )
     */
    public function destroy($id)
    {
        /** @var patient $patient */
        $patient = $this->patientRepository->findWithoutFail($id);

        if (empty($patient)) {
            return $this->sendError('Patient not found');
        }

        $patient->delete();

        return $this->sendResponse($id, 'Patient deleted successfully');
    }
}
