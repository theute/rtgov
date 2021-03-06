/*
 * Copyright 2013 JBoss Inc
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
package org.overlord.rtgov.ui.client.shared.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.overlord.rtgov.ui.client.model.BatchRetryResult;
import org.overlord.rtgov.ui.client.model.SituationBean;
import org.overlord.rtgov.ui.client.model.SituationResultSetBean;
import org.overlord.rtgov.ui.client.model.SituationsFilterBean;
import org.overlord.rtgov.ui.client.model.UiException;

/**
 * Provides a way to manage situations.
 *
 * @author eric.wittmann@redhat.com
 */
@Path("/rest/situations")
public interface ISituationsService {

    /**
     * Search for situations using the given filters and search text.
     * @param filters
     * @param page
     * @param sortColumn
     * @param ascending
     * @throws UiException
     */
    @POST
    @Path("search")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public SituationResultSetBean search(SituationsFilterBean filters,
            @QueryParam("page") int page, @QueryParam("sortColumn") String sortColumn,
            @QueryParam("ascending") boolean ascending) throws UiException;

    /**
     * Fetches a full situation by its id.
     * @param situationId
     * @throws UiException
     */
    @GET
    @Path("situation/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public SituationBean get(@PathParam("id") String situationId) throws UiException;
    
    /**
     * Resubmits a message.
     * @param situationId
     * @param message
     * @throws UiException
     */
    @PUT
    @Path("resubmit/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void resubmit(@PathParam("id") String situationId, String message) throws UiException;
    
    /**
     * Assign a situation to the current uUser.
     * @param sc Security context
     * @param situationId
     * @throws UiException
     */
    @PUT
    @Path("assign/{id}")
    public void assign(@PathParam("id") String situationId) throws UiException;
    
    /**
     * Deassign a situation from an assigned user.
     * @param situationId
     * @throws UiException
     */
    @PUT
    @Path("unassign/{id}")
    public void unassign(@PathParam("id") String situationId) throws UiException;

    /**
     * Updates a situation with the given resolutionState.
     * @param situationId
     * @throws UiException
     */
    @PUT
    @Path("resolution/{id}")
    @Consumes(MediaType.TEXT_PLAIN)
	public void updateResolutionState(@PathParam("id") String situationId, String resolutionState) throws UiException;

	/**
     * Resubmits all situation's matching the given filter.
     * @param situationsFilterBean
     * @throws UiException
     */
    @POST
    @Path("resubmit")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BatchRetryResult resubmit(SituationsFilterBean situationsFilterBean) throws UiException;

    /**
     * Deletes all situation's matching the given filter.
     * @param situationsFilterBean
     * @throws UiException
     */
    @POST
    @Path("delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public int delete(SituationsFilterBean situationsFilterBean) throws UiException;

}
