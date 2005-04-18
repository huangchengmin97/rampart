/*
 * Copyright 2004,2005 The Apache Software Foundation.
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
package org.apache.axis.description;

/**
 * Class FlowIncludeImpl
 */
public class FlowIncludeImpl implements FlowInclude {
    /**
     * Field in
     */
    private Flow in;

    /**
     * Field out
     */
    private Flow out;

    /**
     * Field fault
     */
    private Flow In_fault;

    private Flow Out_fault;

    /**
     * Method getFaultInFlow
     *
     * @return
     */
    public Flow getFaultInFlow() {
        return In_fault;
    }

    /**
     * Method getInFlow
     *
     * @return
     */
    public Flow getInFlow() {
        return in;
    }

    /**
     * Method getOutFlow
     *
     * @return
     */
    public Flow getOutFlow() {
        return out;
    }

    /**
     * Method setFaultInFlow
     *
     * @param flow
     */
    public void setFaultInFlow(Flow flow) {
        this.In_fault = flow;
    }

    public Flow getFaultOutFlow() {
        return this.Out_fault;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setFaultOutFlow(Flow faultFlow) {
        this.Out_fault = faultFlow;
    }

    /**
     * Method setInFlow
     *
     * @param flow
     */
    public void setInFlow(Flow flow) {
        this.in = flow;
    }

    /**
     * Method setOutFlow
     *
     * @param flow
     */
    public void setOutFlow(Flow flow) {
        this.out = flow;
    }
}
