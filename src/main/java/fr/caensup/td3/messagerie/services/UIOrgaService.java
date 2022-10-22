package fr.caensup.td3.messagerie.services;

import org.springframework.stereotype.Service;

@Service
public class UIOrgaService {
  public String getFormValidation() {
    return "$('.ui.form').form({on: 'blur', 'inline': true, fields:{ name:"
        + " ['empty','maxLength[20]'], aliases: 'empty', domain: 'empty'}});";
  }

  public String ifFormIsValid(String code) {
    return "if($('.ui.form').form('validate form')){" + code + "}";
  }

  public String getURL(String url, String idStr) {
    return "'" + url + "'+" + idStr;
  }

  public String toast(String type, String message) {
    return "$.toast({ class: '" + type + "', message: `" + message + "`});";
  }
}
