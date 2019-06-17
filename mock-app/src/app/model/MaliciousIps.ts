import { Log } from "./log";

export class MaliciousIps extends Log {
    public maliciousIps : Array<String>;
    constructor(ip: string, ts: Date) {
        super(ip, ts);
    }
}