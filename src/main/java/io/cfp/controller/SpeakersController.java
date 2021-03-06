/*
 * Copyright (c) 2016 BreizhCamp
 * [http://breizhcamp.org]
 *
 * This file is part of CFP.io.
 *
 * CFP.io is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package io.cfp.controller;

import io.cfp.dto.Speaker;
import io.cfp.entity.Role;
import io.cfp.entity.Talk;
import io.cfp.service.TalkUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * Created by tmaugin on 15/04/2015.
 */

@RestController
@RequestMapping(value = { "/v0/speakers", "/api/speakers" }, produces = APPLICATION_JSON_UTF8_VALUE)
public class SpeakersController {

    @Autowired
    TalkUserService talks;

    /**
     * Get all sessions
     */
    @ResponseBody
    @Secured(Role.ADMIN)
    public List<Speaker> getSpeakers(@RequestParam(required = false) Talk.State[] state) {
        return talks.findAllSpeaker(state);
    }

    @ResponseBody
    @RequestMapping(value = "/accepted")
    public List<Speaker> getAccepted() {
        return talks.findAllSpeaker(Talk.State.ACCEPTED);
    }

}
