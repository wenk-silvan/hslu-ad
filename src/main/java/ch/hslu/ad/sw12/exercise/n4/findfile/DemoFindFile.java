/*
 * Copyright 2020 Hochschule Luzern Informatik.
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
package ch.hslu.ad.sw12.exercise.n4.findfile;

import java.io.File;

import ch.hslu.ad.helper.Timer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Codevorlage für eine Dateisuche.
 */
public final class DemoFindFile {

    private static final Logger LOG = LogManager.getLogger(ch.hslu.ad.sw12.exercise.n4.findfile.DemoFindFile.class);

    /**
     * Privater Konstruktor.
     */
    private DemoFindFile() {
    }

    /**
     * Main-Demo.
     *
     * @param args not used.
     */
    public static void main(String[] args) {
        final String search = "find.me";
        final File rootDir = new File(System.getProperty("user.home"));
        LOG.info("Start searching '" + search + "' recursive in '" + rootDir + "'");
        Timer.stopWatchNano(LOG, func -> FindFile.findFile(search, rootDir));
        System.out.println();
        LOG.info("Start searching '" + search + "' concurrent in " + rootDir + "'");
        final FindFileTask root = new FindFileTask(search, rootDir);
        final String path = (new Timer<String>()).stopWatchNano(LOG, root::invoke);
        LOG.info(String.format("Found '%s' in '%s'", search, path));
    }
}
