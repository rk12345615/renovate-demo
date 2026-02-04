package in.co.homecredit.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"CUID", "ContractNumber", "TaskNameAlias", "TaskNameResult","ExpirationDate", "Comment"})
public class LoxonMsgProducerRequest{

    @JsonProperty("CUID")
    private String cuid;

    @JsonProperty("ContractNumber")
    private String contractNumber;

    @JsonProperty("TaskNameAlias")
    private String taskNameAlias;

    @JsonProperty("TaskNameResult")
    private String taskNameResult;

    @JsonProperty("ExpirationDate")
    private String expirationDate;

    @JsonProperty("Comment")
    private String comment;

    public String getCuid() {
        return cuid;
    }

    public void setCuid(String cuid) {
        this.cuid = cuid;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getTaskNameAlias() {
        return taskNameAlias;
    }

    public void setTaskNameAlias(String taskNameAlias) {
        this.taskNameAlias = taskNameAlias;
    }

    public String getTaskNameResult() {
        return taskNameResult;
    }

    public void setTaskNameResult(String taskNameResult) {
        this.taskNameResult = taskNameResult;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    }



