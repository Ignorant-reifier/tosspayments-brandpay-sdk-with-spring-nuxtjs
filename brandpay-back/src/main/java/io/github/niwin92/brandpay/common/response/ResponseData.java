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
public class ResponseData<T> {
    @JsonProperty("list")
    @ApiModelProperty(value = "list", example = "[]", position = 2, required = true)
    T list;

    @JsonProperty("result")
    @ApiModelProperty(value = "result", example = "result", position = 3, required = true)
    Result result;


    public void objectToResponseData(JSONObject object){
        JSONObject jsonResult = (JSONObject) object.get("RESULT");
        this.result = Result.builder().code(jsonResult.get("CODE").toString()).message(jsonResult.get("MESSAGE").toString()).build();
    }

}
