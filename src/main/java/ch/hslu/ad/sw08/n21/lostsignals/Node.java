/*
 * Copyright 2020 Hochschule Luzern - Informatik.
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
package ch.hslu.ad.sw08.n21.lostsignals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Node {

    private static final Logger LOG = LogManager.getLogger(Node.class);
    private final String name;

    public Node(String name) {
        this.name = name;
    }

    public void send(final Node node, final String msg) {
        node.send(this, msg);
    }

    public String receive(final Node node, final String msg) {
        return node.name + ": " + msg;
    }

    @Override
    public String toString() {
        return name;
    }
}
