import { Log } from "./log";

export class Threat extends Log {
    public threatId : String;
    constructor(ip: string, ts: Date) {
        super(ip, ts);
    }
}