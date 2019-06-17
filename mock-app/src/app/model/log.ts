export class Log {
    public ipAddress : String;
    public timestamp : Date;
    constructor(ip: string,
                ts: Date) {
                    this.ipAddress = ip;
                    this.timestamp = ts;
                }
}
  