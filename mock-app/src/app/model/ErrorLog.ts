import { Log } from "./log";

export class ErrorLog extends Log {
    public message : String;
    constructor(ip: string, ts: Date) {
        super(ip, ts);
    }
}