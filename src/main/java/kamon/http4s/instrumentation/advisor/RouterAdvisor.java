/*
 * =========================================================================================
 * Copyright © 2013-2018 the kamon project <http://kamon.io/>
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 * =========================================================================================
 */

package kamon.http4s.instrumentation.advisor;

import kamon.agent.libs.net.bytebuddy.asm.Advice;
import kamon.http4s.middleware.server.ServerMiddleware;

/**
 * Advisor for org.http4s.server.Router$::apply
 */
public class RouterAdvisor {
    @Advice.OnMethodExit
    public static <F> void exit(@Advice.Return(readOnly = false) cats.data.Kleisli<?, org.http4s.Request<F>, org.http4s.Response<F>> httpService, cats.effect.Sync<F> effect) {
        httpService = ServerMiddleware.apply(httpService, effect);
    }
}