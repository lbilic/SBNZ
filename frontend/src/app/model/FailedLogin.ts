import { Log } from "./log";
import { SystemTypes } from "./systemTypes";

export class FailedLogin extends Log {
    public systemType : SystemTypes;
    public username : String;
    constructor(ip: string, ts: Date) {
        super(ip, ts);
    }
}