import { environment } from '../../../environments/environment';

export class URL {
  private uri: string;
  private parameter: Map<string, number | string>;


  constructor() {
    this.parameter = new Map<string, number | string>();
  }

  addParameter(key: string, parameter: number | string): URL {
    this.parameter.set(key, parameter);
    return this;
  }

  setUri(uri: string): URL {
    this.uri = uri;
    return this;
  }

  buildUrl(): string {
    let url = environment.serverUrl + this.uri;
    const entries = Array.from(this.parameter.entries());
    const size = entries.length;

    if (size > 0) {

      let i = 0;

      url = url + '?';

      while (i < size - 1) {
        url = url + entries[ i ][ 0 ] + '=' + entries[ i ][ 1 ] + '&';
        i++;
      }

      url = url + entries[ i ][ 0 ] + '=' + entries[ i ][ 1 ];

      this.parameter.forEach((value, key) => {
        url = url + value + '=' + key;
      });
    }

    return url;
  }
}
