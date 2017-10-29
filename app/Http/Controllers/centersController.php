<?php

namespace App\Http\Controllers;

use App\DataTables\centersDataTable;
use App\Http\Requests;
use App\Http\Requests\CreatecentersRequest;
use App\Http\Requests\UpdatecentersRequest;
use App\Repositories\centersRepository;
use Flash;
use App\Http\Controllers\AppBaseController;
use Response;

class centersController extends AppBaseController
{
    /** @var  centersRepository */
    private $centersRepository;

    public function __construct(centersRepository $centersRepo)
    {
        $this->centersRepository = $centersRepo;
        $this->middleware('auth');
    }

    /**
     * Display a listing of the centers.
     *
     * @param centersDataTable $centersDataTable
     * @return Response
     */
    public function index(centersDataTable $centersDataTable)
    {
        return $centersDataTable->render('centers.index');
    }

    /**
     * Show the form for creating a new centers.
     *
     * @return Response
     */
    public function create()
    {
        //return \App\Models\centers::find(1)->patients;
        return view('centers.create');
    }

    /**
     * Store a newly created centers in storage.
     *
     * @param CreatecentersRequest $request
     *
     * @return Response
     */
    public function store(CreatecentersRequest $request)
    {
        $input = $request->all();
        //dd($input);
        $centers = $this->centersRepository->create($input);

        Flash::success(trans('custom.centers_saved'));

        return redirect(route('centers.index'));
    }

    /**
     * Display the specified centers.
     *
     * @param  int $id
     *
     * @return Response
     */
    public function show($id)
    {
        $centers = $this->centersRepository->findWithoutFail($id);

        if (empty($centers)) {
            Flash::error(trans('custom.centers_not_found'));

            return redirect(route('centers.index'));
        }

        return view('centers.show')->with('centers', $centers);
    }

    /**
     * Show the form for editing the specified centers.
     *
     * @param  int $id
     *
     * @return Response
     */
    public function edit($id)
    {
        $centers = $this->centersRepository->findWithoutFail($id);

        if (empty($centers)) {
            Flash::error(trans('custom.centers_not_found'));

            return redirect(route('centers.index'));
        }

        return view('centers.edit')->with('centers', $centers);
    }

    /**
     * Update the specified centers in storage.
     *
     * @param  int              $id
     * @param UpdatecentersRequest $request
     *
     * @return Response
     */
    public function update($id, UpdatecentersRequest $request)
    {
        $centers = $this->centersRepository->findWithoutFail($id);

        if (empty($centers)) {
            Flash::error(trans('custom.centers_not_found'));

            return redirect(route('centers.index'));
        }

        $centers = $this->centersRepository->update($request->all(), $id);

        Flash::success(trans('custom.centers_updated'));

        return redirect(route('centers.index'));
    }

    /**
     * Remove the specified centers from storage.
     *
     * @param  int $id
     *
     * @return Response
     */
    public function destroy($id)
    {
        $centers = $this->centersRepository->findWithoutFail($id);

        if (empty($centers)) {
            Flash::error(trans('custom.centers_not_found'));

            return redirect(route('centers.index'));
        }

        $this->centersRepository->delete($id);

        Flash::success(trans('custom.centers_deleted'));

        return redirect(route('centers.index'));
    }
}
