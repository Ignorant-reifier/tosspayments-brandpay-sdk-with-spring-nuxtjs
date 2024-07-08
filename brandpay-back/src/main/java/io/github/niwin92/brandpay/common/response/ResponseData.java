package io.github.niwin92.brandpay.common.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ResponseData {
    @JsonProperty("count")
    @ApiModelProperty(value = "count", example = "1", position = 1, required = true)
    Long count;

    @JsonProperty("list")
    @ApiModelProperty(value = "list", example = "[]", position = 2, required = true)
    JSONArray list;

    @JsonProperty("result")
    @ApiModelProperty(value = "result", example = "result", position = 3, required = true)
    Result result;

    @JsonProperty("callSeq")
    @ApiModelProperty(value = "callSeq", example = "1", position = 4, required = true)
    String callSeq;

    /**
     * 서울시 JSONObject Convert
     * @param object JSONObject
     */
    public void objectToResponseData(JSONObject object){

        if(object.get("count") != null ){
            this.count = (Long) object.get("count");
        } else if(object.get("list_total_count") != null) {
            this.count = (Long) object.get("list_total_count");
        }
        JSONObject jsonResult = (JSONObject) object.get("RESULT");
        this.result = Result.builder().code(jsonResult.get("CODE").toString()).message(jsonResult.get("MESSAGE").toString()).build();
        this.callSeq = (String) object.get("CALL_SEQ");
        this.list = (JSONArray) object.get("row");
    }

}
