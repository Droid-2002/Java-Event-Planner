package model;

// Modelo de um lembrete. Cada objeto e uma linha do reminders.csv (secao 4.2).
// Relacao 1:N com Event: um evento pode ter varios reminders (DT06).
public class Reminder {

    private String reminderId;     // UUID, chave primaria do reminder
    private String eventId;        // FK -> aponta pro id de um Event no events.csv
    private int leadTimeMinutes;   // antecedencia em minutos (ex: 1440 = 1 dia antes)

    public Reminder() {
        // construtor vazio - campos preenchidos pelos setters
    }

    public String getReminderId() {
        return reminderId;
    }

    public void setReminderId(String reminderId) {
        this.reminderId = reminderId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public int getLeadTimeMinutes() {
        return leadTimeMinutes;
    }

    public void setLeadTimeMinutes(int leadTimeMinutes) {
        this.leadTimeMinutes = leadTimeMinutes;
    }
}
