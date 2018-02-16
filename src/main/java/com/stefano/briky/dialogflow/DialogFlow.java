package com.stefano.briky.dialogflow;

import com.stefano.briky.dialogflow.json.QueryResponse;

/**
 * DialogFlow abstraction layer
 */
public interface DialogFlow {

    /**
     * Send query to machine learning and return the result
     * @param query Test to parse
     * @return Machine learning result
     * @throws DialogFlowException
     */
    QueryResponse query(String query) throws DialogFlowException;

}
